import http from "../http-common"; 

class AuctionService {
  getAllAuctions(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/auction/auctions`, searchDTO);
  }

  get(auctionId) {
    return this.getRequest(`/auction/${auctionId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/auction?field=${matchData}`, null);
  }

  addAuction(data) {
    return http.post("/auction/addAuction", data);
  }

  update(data) {
  	return http.post("/auction/updateAuction", data);
  }
  
  uploadImage(data,auctionId) {
  	return http.postForm("/auction/uploadImage/"+auctionId, data);
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

export default new AuctionService();
