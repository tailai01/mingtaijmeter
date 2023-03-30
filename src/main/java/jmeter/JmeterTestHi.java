package jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

//创建一个类继承AbstractJavaSamplerClient
public class JmeterTestHi extends AbstractJavaSamplerClient{
    //①方法的重写只能放生在子父类中；②方法名一致，参数列表也一致③方法重写一般是用来扩展父类
    //重写runTest方法
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sr = new SampleResult();
        sr.sampleStart();
        TestHi hi = new TestHi();
        //获取jmeter的参数，参数名是name，这个是在jmeter中设置的参数，也可以是参数化。
        String username = javaSamplerContext.getParameter("name");
        String str = hi.hi(username);
        //设置响应数据，会写道结果树里面的response body
        sr.setResponseData(str,null);
        sr.setSuccessful(true);
        sr.sampleEnd();
        return sr;
    }

    @Override
    //设置默认参数
    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument("name","zhangsan");
        arguments.addArgument("password","");
        return arguments;
    }


}