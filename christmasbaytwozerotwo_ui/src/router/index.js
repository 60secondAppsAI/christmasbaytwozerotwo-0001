import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Users from  '@/pages/Users.vue';
import UserDetail from  '@/pages/UserDetail.vue';
import Items from  '@/pages/Items.vue';
import ItemDetail from  '@/pages/ItemDetail.vue';
import Bids from  '@/pages/Bids.vue';
import BidDetail from  '@/pages/BidDetail.vue';
import Categorys from  '@/pages/Categorys.vue';
import CategoryDetail from  '@/pages/CategoryDetail.vue';
import PaymentMethods from  '@/pages/PaymentMethods.vue';
import PaymentMethodDetail from  '@/pages/PaymentMethodDetail.vue';
import Orders from  '@/pages/Orders.vue';
import OrderDetail from  '@/pages/OrderDetail.vue';
import Reviews from  '@/pages/Reviews.vue';
import ReviewDetail from  '@/pages/ReviewDetail.vue';
import Addresss from  '@/pages/Addresss.vue';
import AddressDetail from  '@/pages/AddressDetail.vue';
import Carts from  '@/pages/Carts.vue';
import CartDetail from  '@/pages/CartDetail.vue';
import CartItems from  '@/pages/CartItems.vue';
import CartItemDetail from  '@/pages/CartItemDetail.vue';
import Sellers from  '@/pages/Sellers.vue';
import SellerDetail from  '@/pages/SellerDetail.vue';
import Shippings from  '@/pages/Shippings.vue';
import ShippingDetail from  '@/pages/ShippingDetail.vue';
import Wishlists from  '@/pages/Wishlists.vue';
import WishlistDetail from  '@/pages/WishlistDetail.vue';
import WishlistItems from  '@/pages/WishlistItems.vue';
import WishlistItemDetail from  '@/pages/WishlistItemDetail.vue';
import Messages from  '@/pages/Messages.vue';
import MessageDetail from  '@/pages/MessageDetail.vue';
import Auctions from  '@/pages/Auctions.vue';
import AuctionDetail from  '@/pages/AuctionDetail.vue';
import Feedbacks from  '@/pages/Feedbacks.vue';
import FeedbackDetail from  '@/pages/FeedbackDetail.vue';
import Notifications from  '@/pages/Notifications.vue';
import NotificationDetail from  '@/pages/NotificationDetail.vue';
import Promotions from  '@/pages/Promotions.vue';
import PromotionDetail from  '@/pages/PromotionDetail.vue';
import Invoices from  '@/pages/Invoices.vue';
import InvoiceDetail from  '@/pages/InvoiceDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/users',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/users',
		name: 'Users',
		layout: DefaultLayout,
		component: Users,
	},
	{
	    path: '/user/:userId', 
	    name: 'UserDetail',
		layout: DefaultLayout,
	    component: UserDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/items',
		name: 'Items',
		layout: DefaultLayout,
		component: Items,
	},
	{
	    path: '/item/:itemId', 
	    name: 'ItemDetail',
		layout: DefaultLayout,
	    component: ItemDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bids',
		name: 'Bids',
		layout: DefaultLayout,
		component: Bids,
	},
	{
	    path: '/bid/:bidId', 
	    name: 'BidDetail',
		layout: DefaultLayout,
	    component: BidDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/categorys',
		name: 'Categorys',
		layout: DefaultLayout,
		component: Categorys,
	},
	{
	    path: '/category/:categoryId', 
	    name: 'CategoryDetail',
		layout: DefaultLayout,
	    component: CategoryDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/paymentMethods',
		name: 'PaymentMethods',
		layout: DefaultLayout,
		component: PaymentMethods,
	},
	{
	    path: '/paymentMethod/:paymentMethodId', 
	    name: 'PaymentMethodDetail',
		layout: DefaultLayout,
	    component: PaymentMethodDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/orders',
		name: 'Orders',
		layout: DefaultLayout,
		component: Orders,
	},
	{
	    path: '/order/:orderId', 
	    name: 'OrderDetail',
		layout: DefaultLayout,
	    component: OrderDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reviews',
		name: 'Reviews',
		layout: DefaultLayout,
		component: Reviews,
	},
	{
	    path: '/review/:reviewId', 
	    name: 'ReviewDetail',
		layout: DefaultLayout,
	    component: ReviewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/addresss',
		name: 'Addresss',
		layout: DefaultLayout,
		component: Addresss,
	},
	{
	    path: '/address/:addressId', 
	    name: 'AddressDetail',
		layout: DefaultLayout,
	    component: AddressDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/carts',
		name: 'Carts',
		layout: DefaultLayout,
		component: Carts,
	},
	{
	    path: '/cart/:cartId', 
	    name: 'CartDetail',
		layout: DefaultLayout,
	    component: CartDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/cartItems',
		name: 'CartItems',
		layout: DefaultLayout,
		component: CartItems,
	},
	{
	    path: '/cartItem/:cartItemId', 
	    name: 'CartItemDetail',
		layout: DefaultLayout,
	    component: CartItemDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/sellers',
		name: 'Sellers',
		layout: DefaultLayout,
		component: Sellers,
	},
	{
	    path: '/seller/:sellerId', 
	    name: 'SellerDetail',
		layout: DefaultLayout,
	    component: SellerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/shippings',
		name: 'Shippings',
		layout: DefaultLayout,
		component: Shippings,
	},
	{
	    path: '/shipping/:shippingId', 
	    name: 'ShippingDetail',
		layout: DefaultLayout,
	    component: ShippingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/wishlists',
		name: 'Wishlists',
		layout: DefaultLayout,
		component: Wishlists,
	},
	{
	    path: '/wishlist/:wishlistId', 
	    name: 'WishlistDetail',
		layout: DefaultLayout,
	    component: WishlistDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/wishlistItems',
		name: 'WishlistItems',
		layout: DefaultLayout,
		component: WishlistItems,
	},
	{
	    path: '/wishlistItem/:wishlistItemId', 
	    name: 'WishlistItemDetail',
		layout: DefaultLayout,
	    component: WishlistItemDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/messages',
		name: 'Messages',
		layout: DefaultLayout,
		component: Messages,
	},
	{
	    path: '/message/:messageId', 
	    name: 'MessageDetail',
		layout: DefaultLayout,
	    component: MessageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/auctions',
		name: 'Auctions',
		layout: DefaultLayout,
		component: Auctions,
	},
	{
	    path: '/auction/:auctionId', 
	    name: 'AuctionDetail',
		layout: DefaultLayout,
	    component: AuctionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/feedbacks',
		name: 'Feedbacks',
		layout: DefaultLayout,
		component: Feedbacks,
	},
	{
	    path: '/feedback/:feedbackId', 
	    name: 'FeedbackDetail',
		layout: DefaultLayout,
	    component: FeedbackDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notifications',
		name: 'Notifications',
		layout: DefaultLayout,
		component: Notifications,
	},
	{
	    path: '/notification/:notificationId', 
	    name: 'NotificationDetail',
		layout: DefaultLayout,
	    component: NotificationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/promotions',
		name: 'Promotions',
		layout: DefaultLayout,
		component: Promotions,
	},
	{
	    path: '/promotion/:promotionId', 
	    name: 'PromotionDetail',
		layout: DefaultLayout,
	    component: PromotionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/invoices',
		name: 'Invoices',
		layout: DefaultLayout,
		component: Invoices,
	},
	{
	    path: '/invoice/:invoiceId', 
	    name: 'InvoiceDetail',
		layout: DefaultLayout,
	    component: InvoiceDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
