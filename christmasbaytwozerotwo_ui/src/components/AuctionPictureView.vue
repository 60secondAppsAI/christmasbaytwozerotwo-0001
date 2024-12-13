<template> 





</template>
  
<script>

export default {
    components: {
    },
    props: {
        auctions: Array,
    },

    methods: {

    },
};
</script>
  
<style scoped>
.card-container {
    display: flex;
    column-gap: 15px;
    flex-wrap: wrap;
    margin: 5px;

}

.card {
    max-width: 230px;

}

.auction-picture-view {
    margin-bottom: 15px;
    /* Separate each row from the content below */
}

.auction-card {
    max-width: 250px;
    min-width: 250px;
    border-radius: 30px;
    padding: 0px;
    margin-bottom: 25px;
    /* Separate each card */
}

.auction-image {
				    	width: 250px;
	    	height: 200px;
	    	        	border-radius: 4px;
    }

.auction-info {
    margin-top: 0px;
}

.auction-name {
    font-weight: bold;
    font-size: 16px;
    margin-bottom: 2px;
    line-height: 1.2;
}

.auction-details p {
    margin-top: 0px;
    text-align: left;
}

.auction-link {
    display: inline;
    margin-top: 5px;
    color: #1890ff;
}
</style>
  
