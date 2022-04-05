package net.revature.data;

public interface StatusDAO {
	public int getByStatusName(String status);
	public String getByStatusId(int status_id);

}
