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
            <auction-table
            v-if="auctions && auctions.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:auctions="auctions"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-auctions="getAllAuctions"
             >

            </auction-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import AuctionTable from "@/components/AuctionTable";
import AuctionService from "../services/AuctionService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AuctionTable,
  },
  data() {
    return {
      auctions: [],
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
    async getAllAuctions(sortBy='auctionId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AuctionService.getAllAuctions(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.auctions.length) {
					this.auctions = response.data.auctions;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching auctions:", error);
        }
        
      } catch (error) {
        console.error("Error fetching auction details:", error);
      }
    },
  },
  mounted() {
    this.getAllAuctions();
  },
  created() {
    this.$root.$on('searchQueryForAuctionsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAuctions();
    })
  }
};
</script>
<style></style>
