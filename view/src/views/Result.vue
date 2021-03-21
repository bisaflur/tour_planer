<template>
  <div>
    <div class="background"></div>
    <div class="background_white"></div>
    <div class="valign-wrapper row login-box">
      <div class="col card hoverable s10 pull-s1 m10 pull-m1 l8 pull-l2">
          <div class="card-content">
            <div class="row">
              <Weather :info="data" v-for="data in weatherData" :key="data.timeStamp"/>
            </div>
              <Item  :info="pointOfInterest" v-for="pointOfInterest in pointOfInterests" :key="pointOfInterest.id"/>
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
      weatherData: [
        {
          clouds: 'clear sky',
          temperatureCelsiusMax: 4.32,
          temperatureCelsiusMin: -1.67,
          timeStamp: '2021-03-20T21:53:22.39Z[UTC]'
        },
        {
          clouds: 'light snow',
          temperatureCelsiusMax: 7.04,
          temperatureCelsiusMin: 0.09,
          timeStamp: '2021-03-21T21:53:22.39Z[UTC]'
        },
        {
          clouds: 'overcast clouds',
          temperatureCelsiusMax: 6.05,
          temperatureCelsiusMin: -0.24,
          timeStamp: '2021-03-22T21:53:22.39Z[UTC]'
        },
        {
          clouds: 'overcast clouds',
          temperatureCelsiusMax: 9.04,
          temperatureCelsiusMin: 0.47,
          timeStamp: '2021-03-23T21:53:22.39Z[UTC]'
        },
        {
          clouds: 'clear sky',
          temperatureCelsiusMax: 12.63,
          temperatureCelsiusMin: 1.68,
          timeStamp: '2021-03-24T21:53:22.39Z[UTC]'
        },
        {
          clouds: 'overcast clouds',
          temperatureCelsiusMax: 13.87,
          temperatureCelsiusMin: 3.9,
          timeStamp: '2021-03-25T21:53:22.39Z[UTC]'
        },
        {
          clouds: 'light rain',
          temperatureCelsiusMax: 13.04,
          temperatureCelsiusMin: 5.48,
          timeStamp: '2021-03-26T21:53:22.39Z[UTC]'
        },
        {
          clouds: 'light rain',
          temperatureCelsiusMax: 13.33,
          temperatureCelsiusMin: 5.69,
          timeStamp: '2021-03-27T21:53:22.39Z[UTC]'
        }
      ]
    }
  },
  mounted () {
    axios
      .get('http://localhost:9080/weather/getWeather/Heilbronn')
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
