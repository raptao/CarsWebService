package fr.upem.rmi.shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Optional;

/**
 * Created by raptao on 11/2/2016.
 */
public interface MemberDatabase extends Remote{

    /**
     * Returns a member from the database
     * @param memberId the id of the member
     * @param password the password of the member
     * @return an empty {@link Optional} if a member with this id and password is not in the database.
     */
    public Member get(int memberId , String password) throws RemoteException;

    /**
     * Adds a member to the database
     * @param memberType the type of the member
     * @param firstName the firstName of the new member
     * @param lastName the lastName of the new member
     * @param account the account of the new  member
     * @param password the password of the new member
     * @return the new Member added, otherwise null if not added
     */
    public Member add(MemberType memberType, String firstName , String lastName, int account, String password )throws RemoteException;

}
