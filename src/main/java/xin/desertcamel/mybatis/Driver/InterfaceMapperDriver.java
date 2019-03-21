package xin.desertcamel.mybatis.Driver;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import xin.desertcamel.mybatis.Dao.StudentMapper;
import xin.desertcamel.mybatis.Dto.Student;

/**
 * InterfaceMapperDriver
 */
public class InterfaceMapperDriver {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        session.getConfiguration().addMapper(StudentMapper.class);

        StudentMapper mapper = session.getMapper(StudentMapper.class);

        // Create a new student object
        Student student = new Student();

        // Set the values
        student.setName("zara");
        student.setBranch("EEE");
        student.setEmail("zara@gmail.com");
        student.setPercentage(90);
        student.setPhone(123412341);

        // Insert student data
        int insNum = mapper.insert(student);
        if(insNum > 0)
            System.out.println("record inserted successfully");
        else
            System.out.println("record inserted failed");

        session.commit();
        session.close();
    }

}