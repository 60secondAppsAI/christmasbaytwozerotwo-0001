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
            <paymentMethod-table
            v-if="paymentMethods && paymentMethods.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:paymentMethods="paymentMethods"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-payment-methods="getAllPaymentMethods"
             >

            </paymentMethod-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PaymentMethodTable from "@/components/PaymentMethodTable";
import PaymentMethodService from "../services/PaymentMethodService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PaymentMethodTable,
  },
  data() {
    return {
      paymentMethods: [],
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
    async getAllPaymentMethods(sortBy='paymentMethodId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PaymentMethodService.getAllPaymentMethods(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.paymentMethods.length) {
					this.paymentMethods = response.data.paymentMethods;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching paymentMethods:", error);
        }
        
      } catch (error) {
        console.error("Error fetching paymentMethod details:", error);
      }
    },
  },
  mounted() {
    this.getAllPaymentMethods();
  },
  created() {
    this.$root.$on('searchQueryForPaymentMethodsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPaymentMethods();
    })
  }
};
</script>
<style></style>
