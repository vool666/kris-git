<template>
  <div id="app">
    <div id="nav">
      <b-nav tabs>
        <b-nav-item to ="/Bank" exact exact-active-class="active">Bank functions</b-nav-item>
        <b-nav-item to ="/Transactionhistory" exact exact-active-class="active">Transaction history</b-nav-item>
      </b-nav>
    </div>
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
