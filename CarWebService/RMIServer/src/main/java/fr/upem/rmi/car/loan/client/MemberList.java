package fr.upem.rmi.car.loan.client;

import com.google.common.base.Preconditions;
import fr.upem.rmi.shared.interfaces.Member;
import fr.upem.rmi.shared.interfaces.MemberDatabase;
import fr.upem.rmi.shared.interfaces.MemberType;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by raptao on 11/2/2016.
 */
public class MemberList extends UnicastRemoteObject implements MemberDatabase {
    private static int idGenerator;
    private final Collection<Member> members;

    public MemberList() throws RemoteException {
        super();
        this.members = new ArrayList<>();
        idGenerator = 1;
    }

    /**
     * Creates a {@link MemberList} object from a file containing the list of members.
     *
     * @param input path to the file
     * @return
     */
    public static MemberList create(InputStream input) throws IOException {
        Preconditions.checkNotNull(input);
        MemberList memberList = new MemberList();
        List<Member> collect = IOUtils.readLines(input, StandardCharsets.UTF_8).stream()
                .map(line -> line.split(" "))
                .map(getMemberFromLine())
                .filter(member -> member != null)
                .collect(Collectors.toList());
        memberList.members.addAll(collect);
        idGenerator = collect.size() + 1;
        return memberList;
    }

    /**
     * Creates a {@link Member} from a line of a text file.
     * @return the member created.
     */
    private static Function<String[], Member> getMemberFromLine() {
        return tokens -> {
            try {
                int id = Integer.parseInt(tokens[1]);
                int accountId = Integer.parseInt(tokens[4]);
                double accountBalance = Double.parseDouble(tokens[5]);
                switch (tokens[0].toLowerCase()) { // fetching member type
                    case "student":
                        Student student = new Student(id, tokens[2], tokens[3], accountId);
                        student.setPassword(tokens[6]);
                        student.setAccountBalance(accountBalance);
                        return student;
                    case "teacher":
                        Teacher teacher = new Teacher(id, tokens[2], tokens[3], accountId);
                        teacher.setPassword(tokens[6]);
                        teacher.setAccountBalance(accountBalance);
                        return teacher;
                    default:
                        return null;
                }
            } catch (RemoteException e) {
                return null;
            }
        };
    }

    @Override
    public Member get(int memberId, String password) throws RemoteException {
        Optional<Member> first = members.stream().filter(member -> {
            try {
                return member.getId() == memberId && member.getPassword().equals(password);
            } catch (RemoteException e) {
                return false;
            }
        }).findFirst();
        return first.isPresent() ? first.get() : null;
    }

    @Override
    public Member add(MemberType memberType, String firstName, String lastName, int accountId, String password) throws RemoteException {
        Member newMember = buildMember(memberType, firstName, lastName, accountId);
        if (newMember != null) {
            newMember.setPassword(password);
            members.add(newMember);
            idGenerator++;
        }
        return newMember;
    }

    private void add(Member newMember) {
        members.add(newMember);
    }

    /**
     * Build a new {@link Member} object from the info given in arguments
     *
     * @param memberType the type of member to create
     * @param firstName  the firstName of the member
     * @param lastName   the lastName of the member
     * @param accountId  the account of the member
     * @return null if the memberType is {@link MemberType#OTHER}
     * @throws RemoteException
     */
    private Member buildMember(MemberType memberType, String firstName, String lastName, int accountId) throws RemoteException {
        Member newMember;
        switch (memberType) {
            case STUDENT:
                newMember = new Student(idGenerator, firstName, lastName, accountId);
                break;
            case TEACHER:
                newMember = new Teacher(idGenerator, firstName, lastName, accountId);
                break;
            default:
                newMember = null;
                break;
        }
        return newMember;
    }

    public Collection<Member> members() {
        return Collections.unmodifiableCollection(members);
    }
}
