<template>
  <div>
    <div class="background"></div>
    <div class="background_white"></div>
    <div class="valign-wrapper row login-box">
      <div class="col card hoverable s10 pull-s1 m10 pull-m1 l8 pull-l2">
      <form>
          <div class="card-content">
          <button class="btn waves-effect waves-light" type="submit" name="action">Submit
            <i class="material-icons right">send</i>
          </button>
          <Item/>
            <button class="btn waves-effect waves-light" type="submit" name="action">Submit
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

export default {
  name: 'Plan',
  components: {
    Item
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
