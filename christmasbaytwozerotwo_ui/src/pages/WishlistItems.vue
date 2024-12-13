<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <wishlistItem-table
            v-if="wishlistItems && wishlistItems.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:wishlistItems="wishlistItems"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-wishlist-items="getAllWishlistItems"
             >

            </wishlistItem-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import WishlistItemTable from "@/components/WishlistItemTable";
import WishlistItemService from "../services/WishlistItemService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    WishlistItemTable,
  },
  data() {
    return {
      wishlistItems: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllWishlistItems(sortBy='wishlistItemId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await WishlistItemService.getAllWishlistItems(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.wishlistItems.length) {
					this.wishlistItems = response.data.wishlistItems;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching wishlistItems:", error);
        }
        
      } catch (error) {
        console.error("Error fetching wishlistItem details:", error);
      }
    },
  },
  mounted() {
    this.getAllWishlistItems();
  },
  created() {
    this.$root.$on('searchQueryForWishlistItemsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllWishlistItems();
    })
  }
};
</script>
<style></style>
