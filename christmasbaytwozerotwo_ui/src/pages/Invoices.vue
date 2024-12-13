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
            <invoice-table
            v-if="invoices && invoices.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:invoices="invoices"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-invoices="getAllInvoices"
             >

            </invoice-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import InvoiceTable from "@/components/InvoiceTable";
import InvoiceService from "../services/InvoiceService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    InvoiceTable,
  },
  data() {
    return {
      invoices: [],
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
    async getAllInvoices(sortBy='invoiceId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await InvoiceService.getAllInvoices(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.invoices.length) {
					this.invoices = response.data.invoices;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching invoices:", error);
        }
        
      } catch (error) {
        console.error("Error fetching invoice details:", error);
      }
    },
  },
  mounted() {
    this.getAllInvoices();
  },
  created() {
    this.$root.$on('searchQueryForInvoicesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllInvoices();
    })
  }
};
</script>
<style></style>
