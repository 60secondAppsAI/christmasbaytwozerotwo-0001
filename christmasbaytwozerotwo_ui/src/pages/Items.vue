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
            <item-table
            v-if="items && items.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:items="items"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-items="getAllItems"
             >

            </item-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ItemTable from "@/components/ItemTable";
import ItemService from "../services/ItemService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ItemTable,
  },
  data() {
    return {
      items: [],
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
    async getAllItems(sortBy='itemId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ItemService.getAllItems(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.items.length) {
					this.items = response.data.items;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching items:", error);
        }
        
      } catch (error) {
        console.error("Error fetching item details:", error);
      }
    },
  },
  mounted() {
    this.getAllItems();
  },
  created() {
    this.$root.$on('searchQueryForItemsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllItems();
    })
  }
};
</script>
<style></style>
