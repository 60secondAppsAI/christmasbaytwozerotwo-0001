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
            <cartItem-table
            v-if="cartItems && cartItems.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:cartItems="cartItems"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-cart-items="getAllCartItems"
             >

            </cartItem-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CartItemTable from "@/components/CartItemTable";
import CartItemService from "../services/CartItemService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CartItemTable,
  },
  data() {
    return {
      cartItems: [],
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
    async getAllCartItems(sortBy='cartItemId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CartItemService.getAllCartItems(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.cartItems.length) {
					this.cartItems = response.data.cartItems;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching cartItems:", error);
        }
        
      } catch (error) {
        console.error("Error fetching cartItem details:", error);
      }
    },
  },
  mounted() {
    this.getAllCartItems();
  },
  created() {
    this.$root.$on('searchQueryForCartItemsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCartItems();
    })
  }
};
</script>
<style></style>
