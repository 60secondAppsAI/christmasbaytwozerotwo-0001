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
            <seller-table
            v-if="sellers && sellers.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:sellers="sellers"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-sellers="getAllSellers"
             >

            </seller-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SellerTable from "@/components/SellerTable";
import SellerService from "../services/SellerService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SellerTable,
  },
  data() {
    return {
      sellers: [],
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
    async getAllSellers(sortBy='sellerId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SellerService.getAllSellers(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.sellers.length) {
					this.sellers = response.data.sellers;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching sellers:", error);
        }
        
      } catch (error) {
        console.error("Error fetching seller details:", error);
      }
    },
  },
  mounted() {
    this.getAllSellers();
  },
  created() {
    this.$root.$on('searchQueryForSellersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSellers();
    })
  }
};
</script>
<style></style>
