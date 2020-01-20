<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
       <q-toolbar class="bg-red-14 text-white" >
        <q-btn
          flat
          dense
          round
          @click="leftDrawerOpen = !leftDrawerOpen"
          icon="menu"
          class="q-mr-sm"
          aria-label="Menu"
        />

        <q-toolbar-title>
        Aplikasi Dealer KONDA(Admin)
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
      content-class="bg-red-14 text-white"
    >
      <q-list>
        <q-item-label header class="text-white">DAFTAR MENU</q-item-label>
        <q-item  clickable tag="a" target="_blank" class= "text-white">
          <q-item-section avatar>
            <q-icon name="home" />
          </q-item-section>
          <q-item-section>
            <q-btn label="BERANDA" to="/home" type="submit" color="red-14"/>
          </q-item-section>
        </q-item>
        <q-item  clickable tag="a" target="_blank" class= "text-white">
          <q-item-section avatar>
            <q-icon name="person" />
          </q-item-section>
          <q-item-section>
            <q-btn label="DAFTARIN ADMIN" to="/registrasi" type="submit" color="red-14"/>
          </q-item-section>
        </q-item>
          <q-item  clickable tag="a" target="_blank" class= "text-white">
          <q-item-section avatar>
            <q-icon name="motorcycle" />
          </q-item-section>
          <q-item-section>
           <q-btn label="DAFTARIN MOTOR" to="/daftarmotor" type="submit" color="red-14"/>
          </q-item-section>
        </q-item>
             <q-item clickable tag="a" target="_blank">
          <q-item-section avatar>
            <q-icon name="list" />
          </q-item-section>
          <q-item-section>
           <q-btn label="LIST MOTOR" to="/listmotor" type="submit" color="red-14"/>
          </q-item-section>
        </q-item>
         <q-item clickable tag="a" target="_blank">
          <q-item-section avatar>
            <q-icon name="list" />
          </q-item-section>
          <q-item-section>
          <q-btn label="LIST CUSTOMER" to="/listcustomer" type="submit" color="red-14"/>
          </q-item-section>
        </q-item>
        <q-item clickable tag="a" target="_blank">
          <q-item-section avatar>
            <q-icon name="message" />
          </q-item-section>
          <q-item-section>
            <q-btn label="LIHAT PEMESANAN" to="/pemesanan" type="submit" color="red-14"/>
          </q-item-section>
        </q-item>
          <q-item clickable tag="a" target="_blank">
          <q-item-section avatar>
            <q-icon name="money" />
          </q-item-section>
          <q-item-section>
            <q-btn label="BUAT TRANSAKSI" to="/transaksi" type="submit" color="red-14"/>
          </q-item-section>
        </q-item>
        <q-item clickable tag="a" target="_blank" >
          <q-item-section avatar>
            <q-icon name="list" />
          </q-item-section>
          <q-item-section>
          <q-btn label="DAFTAR TRANSAKSI" to="/caritransaksi" type="submit" color="red-14"/>
          </q-item-section>
        </q-item>
              <q-item clickable tag="a" target="_blank" >
          <q-item-section avatar>
            <q-icon name="logout" />
          </q-item-section>
          <q-item-section>
          <q-btn label="LOGOUT" to="/login" type="submit" color="red-14"/>
          </q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: 'MyLayout',

  data () {
    return {
      leftDrawerOpen: false
    }
  }
}
</script>
