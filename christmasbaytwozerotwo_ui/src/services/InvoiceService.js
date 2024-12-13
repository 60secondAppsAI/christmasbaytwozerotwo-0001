import http from "../http-common"; 

class InvoiceService {
  getAllInvoices(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/invoice/invoices`, searchDTO);
  }

  get(invoiceId) {
    return this.getRequest(`/invoice/${invoiceId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/invoice?field=${matchData}`, null);
  }

  addInvoice(data) {
    return http.post("/invoice/addInvoice", data);
  }

  update(data) {
  	return http.post("/invoice/updateInvoice", data);
  }
  
  uploadImage(data,invoiceId) {
  	return http.postForm("/invoice/uploadImage/"+invoiceId, data);
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

export default new InvoiceService();
