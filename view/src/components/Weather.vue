<template>

  <div class="col l3 m4 s6">
    <div class="row valign-wrapper">
    <div class="col s5 paddingZero">
      <img v-if="info.clouds.includes('clear')" class="weather-img" src="../assets/weatherIcons/day_clear.svg">
      <img v-else-if="info.clouds.includes('snow')" class="weather-img" src="../assets/weatherIcons/snow.svg">
      <img v-else-if="info.clouds.includes('clouds')" class="weather-img" src="../assets/weatherIcons/cloudy.svg">
      <img v-else-if="info.clouds.includes('rain')" class="weather-img" src="../assets/weatherIcons/rain.svg">
      <img v-else class="weather-img" src="../assets/logo.png">
    </div>
    <div class="col s7">
      <div class="row weather-text-container">
        <h2 class="weather-text">{{ trimTemp }}</h2>
      </div>
      <div class="row">
        <p> {{ splitDate }} </p>
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
  },
  computed: {
    splitDate: function () {
      return (this.info.timeStamp || '').split('T')[0]
    },
    trimTemp: function () {
      return this.info.temperatureCelsiusMax.toFixed(1) + ' C°'
    }
  }
}

</script>

<style scoped>
.weather-img{
  width: 100%;
}
.paddingZero{
  padding:0 !important;
}
.weather-text{
  font-size: 2rem;
  margin: 0;
}
.weather-text-container{
  margin: 0;
  white-space: nowrap;
}
.row .col.ten2{
  width: 20%;
  margin-left: auto;
  left: auto;
  right: auto;
}
</style>
