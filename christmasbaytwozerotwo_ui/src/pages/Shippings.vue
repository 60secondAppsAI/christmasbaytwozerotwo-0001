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
            <shipping-table
            v-if="shippings && shippings.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:shippings="shippings"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-shippings="getAllShippings"
             >

            </shipping-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ShippingTable from "@/components/ShippingTable";
import ShippingService from "../services/ShippingService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ShippingTable,
  },
  data() {
    return {
      shippings: [],
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
    async getAllShippings(sortBy='shippingId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ShippingService.getAllShippings(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.shippings.length) {
					this.shippings = response.data.shippings;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching shippings:", error);
        }
        
      } catch (error) {
        console.error("Error fetching shipping details:", error);
      }
    },
  },
  mounted() {
    this.getAllShippings();
  },
  created() {
    this.$root.$on('searchQueryForShippingsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllShippings();
    })
  }
};
</script>
<style></style>
