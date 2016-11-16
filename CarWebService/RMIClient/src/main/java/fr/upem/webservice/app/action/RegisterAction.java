package fr.upem.webservice.app.action;

import fr.upem.rmi.shared.interfaces.Member;
import fr.upem.rmi.shared.interfaces.MemberType;
import fr.upem.webservice.app.CarWebService;

import java.rmi.RemoteException;

/**
 * Created by raptao on 11/5/2016.
 */
public class RegisterAction extends ClientAction {

    private String firstName;
    private String lastName;
    private String password;
    private String reg_password_confirm;

    public String getReg_password_confirm() {
        return reg_password_confirm;
    }

    public void setReg_password_confirm(String reg_password_confirm) {
        this.reg_password_confirm = reg_password_confirm;
    }

    private String memberType;
    private String accountId;

    public RegisterAction() {
        super();
    }

    private Member member;

    public Member getMember() {
        return member;
    }

    public String getPassword() throws RemoteException {
        return password;
    }

    public String execute() throws Exception {
        CarWebService carWebService = CarWebService.getInstance();
        MemberType type = memberType.equals("student") ? MemberType.STUDENT : MemberType.TEACHER;
        member = carWebService.addMember(type, firstName, lastName, Integer.parseInt(accountId), password);
        if( member == null || !reg_password_confirm.equals(password)){
            return ERROR;
        }
        return SUCCESS;
    }

    public void setPassword(String password) throws RemoteException {
        this.password = password;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
