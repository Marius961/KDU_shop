export default {
    isUserHasRole(role) {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user && user.roles) {
            return user.roles.indexOf(role.toUpperCase()) >= 0
        } else return false;
    }
}
