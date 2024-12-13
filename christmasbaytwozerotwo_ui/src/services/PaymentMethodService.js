import http from "../http-common"; 

class PaymentMethodService {
  getAllPaymentMethods(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/paymentMethod/paymentMethods`, searchDTO);
  }

  get(paymentMethodId) {
    return this.getRequest(`/paymentMethod/${paymentMethodId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/paymentMethod?field=${matchData}`, null);
  }

  addPaymentMethod(data) {
    return http.post("/paymentMethod/addPaymentMethod", data);
  }

  update(data) {
  	return http.post("/paymentMethod/updatePaymentMethod", data);
  }
  
  uploadImage(data,paymentMethodId) {
  	return http.postForm("/paymentMethod/uploadImage/"+paymentMethodId, data);
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

export default new PaymentMethodService();
