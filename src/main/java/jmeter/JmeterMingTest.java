package jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class JmeterMingTest implements JavaSamplerClient {
    private String mobile;
    private String password;

    public void setupTest(JavaSamplerContext javaSamplerContext) {
        // 从JavaSamplerConext中读取用户传入的参数：mobile和password
        this.mobile = javaSamplerContext.getParameter("mobile");
        this.password = javaSamplerContext.getParameter("password");

    }

    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
        // 获取当前线程编号
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName);
        // 设置返回结果标签的名称,查看结果树的名称
        result.setSampleLabel("ihrm-" + threadName);
        // 在Jmeter的GUI中展示请求数据  request-> request body
        result.setSamplerData("请求的mobile为：" + this.mobile + "\n请求的密码为：" + this.password);

        // 开始事务，开始计算时间
        result.sampleStart();

        TestHi testHi = new TestHi();


        try {
            String response = testHi.ming(this.mobile, this.password);
            // 把返回结果设置到SampleResult中，在jmeter的response_body中展示
            result.setResponseData(response, null);
            // 设置返回结果的为Text类型
            result.setDataType(SampleResult.TEXT);
            result.setSuccessful(true);
            // 输出结果到控制台
            System.out.println(response);
        } catch (Throwable e) {
            result.setSuccessful(false);
            e.printStackTrace();
        } finally {
            // 结束事务，计算请求时间
            result.sampleEnd();
        }
        return result;
    }

    public void teardownTest(JavaSamplerContext javaSamplerContext) {

    }

    public Arguments getDefaultParameters() {
        // 定义Jmeter GUI中java请求的参数：目前在代码中添加了两个参数分别是mobile和password
        // 设置的默认参数，会在GUI中展示出来
        Arguments arguments = new Arguments();
        arguments.addArgument("mobile", "");
        arguments.addArgument("password", "");
        return arguments;
    }
}
