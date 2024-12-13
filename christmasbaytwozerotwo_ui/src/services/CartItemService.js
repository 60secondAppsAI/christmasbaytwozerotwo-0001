import http from "../http-common"; 

class CartItemService {
  getAllCartItems(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/cartItem/cartItems`, searchDTO);
  }

  get(cartItemId) {
    return this.getRequest(`/cartItem/${cartItemId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/cartItem?field=${matchData}`, null);
  }

  addCartItem(data) {
    return http.post("/cartItem/addCartItem", data);
  }

  update(data) {
  	return http.post("/cartItem/updateCartItem", data);
  }
  
  uploadImage(data,cartItemId) {
  	return http.postForm("/cartItem/uploadImage/"+cartItemId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new CartItemService();
