<script setup>
const utils = useUtils()
const catOptions = utils.getAvailableCategories()
    .map(cat => {
      return {
        key: cat.name,
        displayName: cat.displayName
      }
    })
catOptions.unshift({key: 'ANY', displayName: 'Wszystkie kategorie'})

const searchedText = ref('')
const category = ref(catOptions[0].key)
const results = ref([])
const foundEver = ref(false)
const foundCount = computed(() => results.value.length)


const searchValues = async () => {
  foundEver.value = true
  const params = {
    keyWord: searchedText.value
  }
  if (category.value !== 'ANY') {
    params.category = category.value
  }

  results.value = await $fetch('http://localhost:8080/api/products/search', {
    method: 'GET',
    params: params
  })
}
</script>

<template>
  <div>
    <form class="flex justify-center items-center" @submit.prevent="searchValues">
      <div id="searchBar">
        <UInput
            placeholder="Wpisz szukany produkt"
            size="xl"
            v-model="searchedText"
        />
      </div>
      <div id="category" class="mx-10">
        <USelectMenu
            size="xl"
            :options="catOptions"
            value-attribute="key"
            option-attribute="displayName"
            v-model="category"
        />
      </div>
      <UButton type="submit" size="xl">Szukaj</UButton>
    </form>
    <div v-if="foundEver">
      <p class="text-2xl">Znaleziono {{ foundCount }} wyników:</p>
      <ProductSearchListItem
          v-for="result of results"
          :id="result.id"
          :name="result.name"
          :price="result.price"
          :discount-price="result.discountPrice"
      />
    </div>
  </div>
</template>


<style scoped>
#searchBar {
  width: 40%;
}

#category {
  width: 10%;
}
</style>