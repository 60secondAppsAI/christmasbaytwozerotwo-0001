import http from "../http-common"; 

class SellerService {
  getAllSellers(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/seller/sellers`, searchDTO);
  }

  get(sellerId) {
    return this.getRequest(`/seller/${sellerId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/seller?field=${matchData}`, null);
  }

  addSeller(data) {
    return http.post("/seller/addSeller", data);
  }

  update(data) {
  	return http.post("/seller/updateSeller", data);
  }
  
  uploadImage(data,sellerId) {
  	return http.postForm("/seller/uploadImage/"+sellerId, data);
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

export default new SellerService();
