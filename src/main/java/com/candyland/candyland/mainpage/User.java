package com.candyland.candyland.mainpage;

//import object.Id;

/*public class User {
    //@Id;
    //private String lastName;
    //private String firstName;
    private String username;
    private String password;
    private String role;
    private static String currentUser;

    public User(  String username, String password, String role) {

        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return    Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash( username, password, role);
    }

    public static String getUserRole(String username, String password) throws AccountException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
            {
                if (Objects.equals(encodePassword(username,password), user.getPassword()))
                    return user.getRole();
                else
                    throw new IncorrectPasswordException(password);
            }
        }
        throw new UsernameNotExistsException(username);
    }

}*/
