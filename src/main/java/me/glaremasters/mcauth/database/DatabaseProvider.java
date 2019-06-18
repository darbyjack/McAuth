package me.glaremasters.mcauth.database;

public interface DatabaseProvider {

    /**
     * Start up the database
     */
    void init();

    /**
     * Add user to database
     * @param uuid uuid
     * @param token token
     */
    void insertUser(String uuid, String token);

    /**
     * Set the token for the user
     * @param token token
     * @param uuid uuid
     */
    void setToken(String token, String uuid);

    /**
     * Check if user has token
     * @param uuid uuid to check
     * @return token or not
     */
    boolean hasToken(String uuid);

}
