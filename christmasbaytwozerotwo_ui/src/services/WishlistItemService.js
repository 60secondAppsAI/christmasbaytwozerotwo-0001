import http from "../http-common"; 

class WishlistItemService {
  getAllWishlistItems(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/wishlistItem/wishlistItems`, searchDTO);
  }

  get(wishlistItemId) {
    return this.getRequest(`/wishlistItem/${wishlistItemId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/wishlistItem?field=${matchData}`, null);
  }

  addWishlistItem(data) {
    return http.post("/wishlistItem/addWishlistItem", data);
  }

  update(data) {
  	return http.post("/wishlistItem/updateWishlistItem", data);
  }
  
  uploadImage(data,wishlistItemId) {
  	return http.postForm("/wishlistItem/uploadImage/"+wishlistItemId, data);
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

export default new WishlistItemService();
