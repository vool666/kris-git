<template>
  <b-container>

    <div>
<!--      <b-nav tabs>-->
<!--        <b-nav-item active>Bank functions</b-nav-item>-->
<!--        <b-nav-item>Transactions history</b-nav-item>-->
<!--      </b-nav>-->
    </div>

    <b-row align-v="center" class="pt-5">
         <b-col>
           <b-card
               title="Create Customer"
               img-src="https://picsum.photos/1600/200/?image=1042"
               bg-variant="dark"
               text-variant="white"
               tag="article"
               style="max-width:30rem"
               class="mb-2"
          >
             <b-card-text>
               <b-form-input v-model="customers.customerid" placeholder="Customer ID"></b-form-input>
               <b-form-input v-model="customers.accountid" placeholder="Account ID"></b-form-input>
               <b-form-input v-model="customers.eesnimi" placeholder="Eesnimi"></b-form-input>
               <b-form-input v-model="customers.perekonnanimi" placeholder="Perekonnanimi"></b-form-input>
               <b-form-input v-model="customers.username" placeholder="Username"></b-form-input>
               <b-form-input v-model="customers.password" placeholder="Password"></b-form-input>
             </b-card-text>
             <b-button variant="primary" v-on:click="createCustomerHtml()">Execute</b-button>
           </b-card>
           <b-card
               title="Create Account"
               img-src="https://picsum.photos/1600/200/?image=1040"
               bg-variant="dark"
               text-variant="white"
               tag="article"
               style="max-width:30rem"
               class="mb-2">
             <b-card-text>
               <b-form-input v-model="accounts.customerid" placeholder="Customer ID"></b-form-input>
               <b-form-input v-model="accounts.accountid" placeholder="Account ID"></b-form-input>
             </b-card-text>
             <b-button variant="primary" v-on:click="createAccountHtml()">Execute</b-button>
           </b-card>
           <b-card
               title="Get Balance"
               img-src="https://picsum.photos/1600/200/?image=135"
               bg-variant="dark"
               text-variant="white"
               tag="article"
               style="max-width:30rem"
               class="mb-2">
             <b-card-text>
               <b-form-input v-model="balance.accountid" placeholder="Account ID"></b-form-input>
             </b-card-text>
             <b-button variant="primary" v-on:click="getBalanceHtml()">Execute</b-button>
           </b-card>
         </b-col>
         <b-col>
           <b-card
               title="Deposit money"
               img-src="https://picsum.photos/1600/200/?image=144"
               bg-variant="dark"
               text-variant="white"
               tag="article"
               style="max-width:30rem"
               class="mb-2">
             <b-card-text>
               <b-form-input v-model="deposit.accountid" placeholder="Account ID"></b-form-input>
               <b-form-input v-model="deposit.amount" placeholder="Deposit amount"></b-form-input>
             </b-card-text>
             <b-button variant="primary" v-on:click="depositMoneyHtml()">Execute</b-button>
           </b-card>

           <b-card
               title="Withdraw money"
               img-src="https://picsum.photos/1600/200/?image=112"
               bg-variant="dark"
               text-variant="white"
               tag="article"
               style="max-width:30rem"
               class="mb-2">
             <b-card-text>
               <b-form-input v-model="withdraw.accountid" placeholder="Account ID"></b-form-input>
               <b-form-input v-model="withdraw.amount" placeholder="Withdraw amount"></b-form-input>
             </b-card-text>
             <b-button variant="primary" v-on:click="withdrawMoneyHtml()">Execute</b-button>
           </b-card>

           <b-card
               title="Transfer money"
               img-src="https://picsum.photos/1600/200/?image=108"
               bg-variant="dark"
               text-variant="white"
               tag="article"
               style="max-width:30rem"
               class="mb-2">
             <b-card-text>
               <b-form-input v-model="transfer.fromaccountid" placeholder="From account ID"></b-form-input>
               <b-form-input v-model="transfer.toaccountid" placeholder="To account ID"></b-form-input>
               <b-form-input v-model="transfer.amount" placeholder="Transfer amount"></b-form-input>
             </b-card-text>
             <b-button variant="primary" v-on:click="transferMoneyHtml()">Execute</b-button>
           </b-card>
         </b-col>
    </b-row>
  </b-container>

</template>

<script>


let getData = function () {
  this.$http.get('http://localhost:8080/transactionhistory')
      .then(response => this.transactionhistory = response.data)
      .catch(response => console.log(response))
}


let createCustomerJs = function () {

  let createCustomer = {
    customerid: this.customers.customerid,
    accountid: this.customers.accountid,
    eesnimi: this.customers.eesnimi,
    perekonnanimi: this.customers.perekonnanimi,
    username: this.customers.username,
    password: this.customers.password
  }
  this.$http.post('http://localhost:8080/createCustomer', createCustomer)
      .then(() => this.getData());
}

let createAccountJs = function () {

  let createAccount = {
    customerid: this.accounts.customerid,
    accountid: this.accounts.accountid
  }
  this.$http.post('http://localhost:8080/createAccount', createAccount)
      .then(() => this.getData());
}

let getBalanceJs = function () {

  let getBalance = {
    accountid: this.balance.accountid,
  }
  this.$http.get('http://localhost:8080/depositMoney', getBalance)
      .then(() => this.getData());
}

let depositMoneyJs = function () {

  let depositMoney = {
    accountid: this.deposit.accountid,
    amount: this.deposit.amount
  }
  this.$http.put('http://localhost:8080/depositMoney', depositMoney)
      .then(() => this.getData());
}

let withdrawMoneyJs = function () {

  let withdrawMoney = {
    accountid: this.withdraw.accountid,
    amount: this.withdraw.amount
  }
  this.$http.put('http://localhost:8080/withdrawMoney', withdrawMoney)
      .then(() => this.getData());
}

let transferMoneyJs = function () {

  let transferMoney = {
    fromaccountid: this.transfer.accountid,
    toaccountid: this.transfer.accountid,
    amount: this.transfer.amount
  }
  this.$http.put('http://localhost:8080/transfer', transferMoney)
      .then(() => this.getData());
}

export default {
  name: "Bank",

  data: function () {
    return {
      firstName: "",
      accounts: {},
      customers: {},
      balance: {},
      deposit: {},
      withdraw: {},
      transfer: {},
      transactionhistory: []
    }
  }, methods: {
    createCustomerHtml: createCustomerJs,
    createAccountHtml: createAccountJs,
    getBalanceHtml: getBalanceJs,
    depositMoneyHtml: depositMoneyJs,
    withdrawMoneyHtml: withdrawMoneyJs,
    transferMoneyHtml: transferMoneyJs,
    getData: getData
  },
  mounted() {
    this.getData();
  }
}
</script>

<style scoped>
h1 {
  color: #2c3e50;
}

.mb-2 {
  font-family: "Myriad Pro";
}

</style>