package fr.upem.rmi.car.loan.client;

import fr.upem.rmi.shared.interfaces.MemberType;

import java.rmi.RemoteException;

public class Teacher extends AbstractMember {

    /**
     * Initializes a newly created Teacher object
     * @param id
     * @param firstName
     * @param lastName
     * @param accountId
     * @throws RemoteException
     */
	public Teacher(int id, String firstName, String lastName, int accountId) throws RemoteException {
		super(id,firstName,lastName, accountId);
	}

    public Teacher( int id , String firstName, String lastName) throws RemoteException {
        super( id, firstName, lastName, null);
    }

    @Override
    public MemberType type() {
        return MemberType.TEACHER;
    }

}
