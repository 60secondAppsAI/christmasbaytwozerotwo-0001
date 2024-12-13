import http from "../http-common"; 

class ShippingService {
  getAllShippings(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/shipping/shippings`, searchDTO);
  }

  get(shippingId) {
    return this.getRequest(`/shipping/${shippingId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/shipping?field=${matchData}`, null);
  }

  addShipping(data) {
    return http.post("/shipping/addShipping", data);
  }

  update(data) {
  	return http.post("/shipping/updateShipping", data);
  }
  
  uploadImage(data,shippingId) {
  	return http.postForm("/shipping/uploadImage/"+shippingId, data);
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

export default new ShippingService();
