import http from "../http-common"; 

class ItemService {
  getAllItems(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/item/items`, searchDTO);
  }

  get(itemId) {
    return this.getRequest(`/item/${itemId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/item?field=${matchData}`, null);
  }

  addItem(data) {
    return http.post("/item/addItem", data);
  }

  update(data) {
  	return http.post("/item/updateItem", data);
  }
  
  uploadImage(data,itemId) {
  	return http.postForm("/item/uploadImage/"+itemId, data);
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

export default new ItemService();
