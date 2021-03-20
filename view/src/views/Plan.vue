<template>
  <div>
    <div class="background"></div>
    <div class="background_white"></div>
    <div class="valign-wrapper row login-box">
      <div class="col card hoverable s10 pull-s1 m10 pull-m1 l8 pull-l2">
      <form>
          <div class="card-content">
          <button class="btn waves-effect waves-light" type="submit" name="action"  @click.stop.prevent="submit()">Submit
            <i class="material-icons right">send</i>
          </button>
            <div v-for="pointOfInterest in pointOfInterests" :key="pointOfInterest.id">
              <Item  :info="pointOfInterest"/>
            </div>
            <button class="btn waves-effect waves-light" type="submit" name="action"  @click.stop.prevent="submit()">Submit
              <i class="material-icons right">send</i>
            </button>
        </div>
      </form>
    </div>
  </div>
  </div>
</template>

<script>
import Item from '../components/Item.vue'
/* import axios from 'axios' */

export default {
  name: 'Plan',
  components: {
    Item
  },
  /* mounted () {
    axios
      .get('url')
      .then(response => (this.pointOfInterest = response))
  }, */
  data () {
    return {
      resultList: [],
      pointOfInterests: [{
        id: 1,
        name: 'Point of Interest Name',
        category: 'Category'
      }, {
        id: 2,
        name: 'Point of Interest Name 2',
        category: 'Category 2'
      }]
    }
  },
  methods: {
    submit () {
      const checkbox = document.getElementsByTagName('input')
      for (let i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
          this.resultList.push(this.pointOfInterests[i])
        }
      }
      if (this.resultList.length > 0) {
        this.$router.push({
          name: 'Result',
          params: { result: JSON.stringify(this.resultList) }
        })
      } else {
        alert('you did not select any Point of interest')
      }
    }
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
