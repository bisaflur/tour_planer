<template>
  <div>
    <div class="background"></div>
    <div class="background_white"></div>
    <div class="valign-wrapper row login-box">
      <div class="col card hoverable s10 pull-s1 m10 pull-m1 l8 pull-l2">
          <div class="card-content">
            <div class="row">
              <Weather :info="data" v-for="data in weatherData" :key="data.id"/>
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

export default {
  name: 'Result',
  components: {
    Item,
    Weather
  },
  data () {
    return {
      pointOfInterests: JSON.parse(this.$route.params.result),
      weatherData: [{
        id: 1,
        date: '18.03.21',
        temp: '30C',
        look: 'cloudy'
      },
      {
        id: 2,
        date: '19.03.21',
        temp: '26C',
        look: 'sunny'
      },
      {
        id: 3,
        date: '20.03.21',
        temp: '22C',
        look: 'rain'
      },
      {
        id: 4,
        date: '21.03.21',
        temp: '15C',
        look: 'storm'
      },
      {
        id: 5,
        date: '22.03.21',
        temp: '30C',
        look: 'sunny'
      }]
    }
  },
  mounted () {
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
