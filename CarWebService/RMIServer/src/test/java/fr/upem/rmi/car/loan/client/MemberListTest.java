package fr.upem.rmi.car.loan.client;

import fr.upem.rmi.shared.interfaces.Member;
import fr.upem.rmi.shared.interfaces.MemberType;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by raptao on 11/2/2016.
 */
public class MemberListTest {
    @Test
    public void create() throws Exception {
        InputStream path = MemberListTest.class.getResourceAsStream("/members-generator.txt");
        path.mark(0);
        MemberList memberList = MemberList.create(path);
        path.reset();
        assertEquals( memberList.members().size(), IOUtils.readLines(path, StandardCharsets.UTF_8).size());
    }

    @Test
    public void addAndGetMember() throws Exception {
        MemberList list = new MemberList();
        list.add(MemberType.STUDENT, "test", "student", 1, "unsafePassword");
        list.add(MemberType.TEACHER,"test", "teacher", 2, "unsafePassword");

        Teacher teacher = new Teacher(2, "test", "teacher", 2);
        Student s = new Student(1, "test", "student", 1);
        s.setPassword("unsafePassword");
        teacher.setPassword("unsafePassword");
        assertTrue(list.members().contains(s));
        assertTrue(list.members().contains(teacher));
        Member student = list.get(1, "unsafePassword");
        assertTrue(student.equals(s));
    }
}