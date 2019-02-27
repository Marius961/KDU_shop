export default {


    decodeClaim(claim, token) {
        try {
            const parsedToken = JSON.parse(atob(token.split('.')[1]))
            return parsedToken[claim];
        } catch (e) {
            return null;
        }
    }
}
