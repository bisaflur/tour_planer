<template>

  <div>
    <section class="container z-fix">
      <h1 id="title">
        <span v-lettering class="title hide">Plan</span>
        <span v-lettering class="title hide">Your</span>
        <span v-lettering class="title hide">Trip</span>
      </h1>
    </section>
    <div class="background"></div>
    <div class="background_white"></div>
    <div class="valign-wrapper row login-box z-fix">
      <div class="col card hoverable s10 pull-s1 m10 pull-m1 l6 pull-l3">
        <form>
          <div class="card-content">
            <div class="row">
              <div class="input-field col s12 ">
                <input v-model="formCity" id="city" type="text" class="validate" required>
                <label for="city">City</label>
                <span class="helper-text" data-error="wrong" data-success="right"></span>
              </div>
            </div>
            <div class="row">
            <div class="col s9">
              <p class="range-field">
                <input type="range" id="test5" min="0" max="100" v-model="formRange"/>
              </p>
            </div>
            <div class="col s2">
              <input type="number" class="range-input" v-model="formRange" >
            </div>
            <div class="col s1">
              <p>KM</p>
            </div>
          </div>
            <div class="row">
            <div class="input-field col s3">
              <button class="btn waves-effect waves-light" type="submit" name="action" @click.stop.prevent="submit()">Submit
                <i class="material-icons right">send</i>
              </button>
            </div>
          </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>

/* <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
    */

import M from 'materialize-css'
// eslint-disable-next-line import/no-duplicates
import { Back } from 'gsap'
// eslint-disable-next-line import/no-duplicates
import { TimelineMax } from 'gsap'

export default {
  name: 'Request',
  mounted () {
    this.animation()
    M.AutoInit()
    M.Range.init(document.getElementById('test5'))
  },
  data: function () {
    return {
      formRange: 1,
      show: false,
      formCity: ''
    }
  },
  methods: {
    animation: function () {
      setTimeout(function () {
        const title1 = new TimelineMax()
        title1.staggerFromTo('.title span', 0.5,
          { ease: Back.easeOut.config(1.7), opacity: 0, bottom: -80 },
          { ease: Back.easeOut.config(1.7), opacity: 1, bottom: 0 }, 0.05)
        const title = document.getElementsByClassName('title')
        for (let i = 0; i < title.length; i++) {
          title[i].classList.remove('hide')
        }
      }, 1000)
    },
    submit () {
      if (this.formCity !== '') {
        this.$router.push({
          name: 'Plan',
          params: { city: this.formCity, range: this.formRange }
        })
      }
    }
  }
}

</script>
<style scoped>

@import '../assets/scss/Title-animeation.css';
.hide{
  opacity: 0;
}
.z-fix{
  z-index: 2;
}
.container {
  margin-top: 50px;
  width: 200px;
}
.range-input{
  height: 27px !important;
}
.login-box {
  position: absolute;
  top: 0;
  left: 0;
  height: 100vh;
  width: 100vw;
}

.btn {
  margin-top: 10px;
}

.input-field label {
  color: #454249 !important;
}

.validate {
  border-color: #454249 !important;
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
  height: 100vh;
  width: 100vw;
  top: 0;
  left: 0;
}

.background_white {
  background: rgba(255, 255, 255, 0.3);
}

.row {
  text-align: initial;
}

</style>
