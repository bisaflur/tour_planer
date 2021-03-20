<template>

  <div class="col ten2">
    <div class="row valign-wrapper">
    <div class="col l6">
      <img class="weather-img" src="../assets/logo.png">
    </div>
    <div class="col l6">
      <div class="row weather-text-container">
        <h2 class="weather-text">{{ info.temp }}</h2>
      </div>
      <div class="row">
        <p> {{ info.date }} </p>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: 'Weather',
  props: {
    info: Object
  }
}
</script>

<style scoped>
.weather-img{
  width: 100%;
}
.weather-text{
  font-size: 2rem;
  margin: 0;
}
.weather-text-container{
  margin: 0;
}
.row .col.ten2{
  width: 20%;
  margin-left: auto;
  left: auto;
  right: auto;
}
</style>
