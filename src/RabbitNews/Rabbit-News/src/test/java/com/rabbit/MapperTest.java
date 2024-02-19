package com.rabbit;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.rabbit.mapper.UserMapper;
import com.rabbit.utils.BadWordsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
@ConfigurationProperties(prefix="oss")
public class MapperTest {
    //RAM子用户的key值
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String fileName="buluoniya.jpg";
    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    //地域节点(Endpoint)的配置;
    private String endpoint = "oss-cn-zhangjiakou.aliyuncs.com";




    @Autowired
    private UserMapper userMapper;

    @Test
    public void TestBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encode=passwordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Test
    public void Demo ()throws IOException {


        InputStream inputStream=new FileInputStream("C:\\Users\\watermelon\\Downloads\\buluoniya.jpg");


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream);


        // 创建PutObject请求。
        ossClient.putObject(putObjectRequest);

        //文件访问路径
        //String url=endpoint.split("//")+"//"+bucketName+"."+endpoint.split("//")[1]+"/"+fileName;

        ossClient.shutdown();
    }

    @Test
    public void TestBadWord() throws IOException{
        BadWordsUtils.initbadWordsList();
        List<String> words=BadWordsUtils.searchBanWords("习近平");
        words.forEach(System.out::println);
    }
}
