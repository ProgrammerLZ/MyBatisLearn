package xin.desertcamel.mybatis.Driver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import xin.desertcamel.mybatis.Dto.Student;

/**
 * Hello world!
 *
 */
public class XMLMapperDriver {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();

        Student student = new Student();
        student.setBranch("It");
        student.setEmail("411671327@qq.com");
        student.setName("LZ");
        student.setPercentage(90);
        student.setPhone(123);

        //插入
        int insertNum = session.insert("StudentMapper.insert", student);
        if(insertNum > 0)
            System.out.println("Record inserted successfully");

        //查询列表
        Collection studentList = session.selectList("StudentMapper.getAll");
        Iterator iterator = studentList.iterator();

        while(iterator.hasNext()){
            Student student2 = (Student)iterator.next();
            System.out.println(student2.toString());
        }

        //查询单个
        Student student3 = (Student)session.selectOne("StudentMapper.getById", student.getId());//参数直接传student也可以，mybatis会自动根据参数名称到student中找id属性
        System.out.println(student3.toString());

        //更新
        student3.setPhone(456);
        int updateNum = session.update("StudentMapper.update", student);
        if(updateNum > 0)
            System.out.println("Record updated successfully");

        //删除
        int deleteNum = session.delete("StudentMapper.deleteById", student3);
        if(deleteNum > 0)
            System.out.println("Record deleted successfully");


        //调用存储过程
        Student student4 = (Student) session.selectOne("StudentMapper.callById",5);
        System.out.println(student4.toString());
        session.commit();
        session.close();
    }
}
