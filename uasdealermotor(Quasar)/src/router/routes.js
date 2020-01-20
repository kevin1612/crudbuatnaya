
const routes = [
  {
    path: '/home',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') }
    ]
  },
  {
    path: '/login',
    component: () => import('layouts/Guest.vue'),
    children: [
      { path: '', component: () => import('pages/login.vue') }
    ]
  },
  {
    path: '/registrasi',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/registrasi.vue') }
    ]
  },
  {
    path: '/listcustomer',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/listcustomer.vue') }
    ]
  },
  {
    path: '/listmotor',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/listmotor.vue') }
    ]
  },
  {
    path: '/daftarmotor',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/daftarinmotor.vue') }
    ]
  },
  {
    path: '/transaksi',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/transaksi.vue') }
    ]
  },
  {
    path: '/root',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/root.vue') }
    ]
  },
  {
    path: '/caritransaksi',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/caritransaksi.vue') }
    ]
  },
  {
    path: '/pemesanan',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/pemesanan.vue') }
    ]
  },
  {
    path: '/',
    component: () => import('layouts/Guest.vue'),
    children: [
      { path: '', component: () => import('pages/interface.vue') }
    ]
  }
]

// Always leave this as last one
if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '/',
    component: () => import('pages/Index.vue')
  })
}

export default routes
