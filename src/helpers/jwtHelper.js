export default {


    decodeClaim(claim, token) {
        try {
            const parsedToken = JSON.parse(atob(token.split('.')[1]))
            return parsedToken[claim];
        } catch (e) {
            return null;
        }
    },

    getJwtToken() {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user && user.token) {
            return user.token
        } else {
            return null
        }
    },

    getAuthorizationHeader() {
        let token = this.getJwtToken();
        if (typeof token != null) {
            return {
                'Authorization': token
            }
        } else return {};
    }
}
