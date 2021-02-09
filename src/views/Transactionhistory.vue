<template>
  <div>
    <b-container>
      <b-row>
        <b-col>
          <b-table striped hover :items="transactionhistory">
          </b-table>
        </b-col>
        <b-col>

        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>

let getData = function () {
  this.$http.get('http://localhost:8080/transactionhistory')
      .then(response => this.transactionhistory = response.data)
      .catch(response => console.log(response))
}

export default {
  name: "Transactionhistory",

  data: function () {
    return {
      transactionhistory: []
    }
  }, methods: {
    getData: getData
  },
  mounted() {
    this.getData();
  }
}
</script>

<style scoped>

</style>