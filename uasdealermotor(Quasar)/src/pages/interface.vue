<template>
  <q-page class="flex flex-center">
      <q-card-section>
           <img alt="pow" src="~assets/pw1.jpg">
      </q-card-section>
         <div>
             <div class="text-h6 text-white">WELCOME ADMIN</div>
        <div class="text-subtitle2 text-white">Just Click:</div>
              <q-btn label="Login" to="/login" type="submit" color="primary"/>
         </div>
  </q-page>
</template>

<script>
export default {
  name: 'PageIndex'
}
</script>
