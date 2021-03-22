<template>
  <div>
    <div class="valign-wrapper row login-box">
      <div class="col card hoverable s10 pull-s1 m10 pull-m1 l8 pull-l2">
          <div class="card-content">
            <div class="row">
              <Weather :info="data" v-for="data in weatherData.data" :key="data.timeStamp"/>
            </div>
            <Item :info="pointOfInterest" v-for="pointOfInterest in pointOfInterests" :key="pointOfInterest.id"/>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import Item from '../components/ItemContent.vue'
import Weather from '../components/Weather'
import axios from 'axios'

export default {
  name: 'Result',
  components: {
    Item,
    Weather
  },
  data () {
    return {
      pointOfInterests: JSON.parse(this.$route.params.result),
      weatherData: {
        data: []
      }
    }
  },
  mounted () {
    axios
      .get('http://localhost:9080/weather/getWeather/' + this.$route.params.city)
      .then(response => (this.weatherData = response))
  }
}
</script>

<style scoped>
.card{
  background: #f1f1f1;
}
.background {

  background-image: url("../assets/travel_background_2.jpg");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  filter: blur(5px);
  -webkit-filter: blur(5px);
}

.background_white, .background {
  position: absolute;
  z-index: -10;
  height: 100vh;
  width: 100vw;
  top: 0;
  left: 0;
}

.background_white {
  background: rgba(255, 255, 255, 0.3);
}
</style>
