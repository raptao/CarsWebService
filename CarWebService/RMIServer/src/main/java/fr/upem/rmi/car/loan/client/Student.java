package fr.upem.rmi.car.loan.client;

import fr.upem.rmi.shared.interfaces.MemberType;

import java.rmi.RemoteException;

/**
 * Represents a student of the university.
 */
public class Student extends AbstractMember {

    public Student( int id , String firstName, String lastName) throws RemoteException {
        super( id, firstName, lastName, null);
    }

	public Student(int id,String firstName,String lastName, int accountId) throws RemoteException {
		super(id,firstName,lastName, accountId);
	}

    @Override
    public MemberType type() throws RemoteException {
        return MemberType.STUDENT;
    }


}
