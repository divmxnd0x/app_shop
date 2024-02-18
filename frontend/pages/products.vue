<script setup>
const route = useRoute()
const router = useRouter()
const utils = useUtils()

let page = ref(Number(route.query.page) || 1)
let category = ref(route.query.category)
let sortBy = ref(route.query.sortBy)
const totalPages = ref(0)
const elements = ref([])

const handlePageChanged = (value) => {
  page.value = value
}

const handleSortChanged = (value) => {
  sortBy.value = value
  if (sortBy.value === 'NONE') {
    sortBy.value = null
  }
}

const fetch = async () => {
  const params = {
    pageNo: (page.value - 1) || 0
  }
  if (category.value) {
    params.category = category.value
  }
  if (sortBy.value) {
    params.sort = sortBy.value
  }

  const response = await $fetch('http://localhost:8080/api/products/list', {
    method: 'GET',
    params: params
  })

  if (response) {
    totalPages.value = response.totalPages
    elements.value = response.content
  }
}

fetch()

watch(router.currentRoute, () => {
  page.value = Number(route.query.page) || 1
  category.value = route.query.category
  sortBy.value = route.query.sortBy
  fetch()
})

watch([page, category, sortBy], () => {
  router.push({query: {page: page.value, category: category.value}})
  if (sortBy.value) {
    router.push({query: {page: page.value, category: category.value, sortBy: sortBy.value}})
  }
  fetch()
})


</script>

<template>
  <p v-if="category" class="text-2xl">
    Kategoria: {{ utils.getCategoryDisplayName(category) }}
  </p>
  <PageAndSorting :total-pages="totalPages"
                  :page="Number(page)"
                  :current-option="sortBy || 'NONE'"
                  @page-changed="handlePageChanged"
                  @sort-changed="handleSortChanged"
  />

  <div class="grid grid-cols-4 items-center gap-y-24">
    <ProductListItem v-for="item of elements"
                     :id="item.id"
                     :name="item.name"
                     :price="item.price"
                     :measure-unit="item.measure.unit"
                     :measure-value="item.measure.value"
                     :price-per-measure="item.pricePerMeasure"
                     :key="item.id"
                     :discount-price="item.discountPrice"
    />
  </div>

</template>