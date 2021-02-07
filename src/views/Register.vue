<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    Account ID : <input v-model="accounts.accountid">
    Deposit amount : <input v-model="accounts.amount">
    <button v-on:click="saveInHtml()">Päring</button>
    <table border="1">
      <tr>
        <th>accountid</th>
        <th>deposit</th>
        <th>withdraw</th>
        <th>time</th>
      </tr>
      <tr v-for="row in transactionhistory">
        <td>{{row.accountid}}</td>
        <td>{{row.deposit}}</td>
        <td>{{row.withdraw}}</td>
        <td>{{row.time}}</td>
      </tr>
    </table>
  </div>
</template>

<script>
// @ is an alias to /src
let getData = function () {
  this.$http.get('http://localhost:8080/transactionhistory')
  .then(response => this.transactionhistory = response.data)
  .catch(response => console.log(response))
}


let saveInJs = function(){

  let depositMoney = {
    accountid: this.accounts.accountid,
    amount: this.accounts.amount
  }
  this.$http.put('http://localhost:8080/depositMoney', depositMoney)
  .then(() => this.getData());
}

export default {
  name: 'Register',
  components: {
  },
  data: function () {
    return {
      firstName: "",
      accounts: {},
      transactionhistory: []
    }
  }, methods: {
    saveInHtml: saveInJs,
    getData: getData
  },
  mounted() {
    this.getData();
  }
}
</script>
