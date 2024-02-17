<script setup>
  // definePageMeta({
  //   layout: null
  // })
</script>

<template>
  <div id="main-page" class="h-screen flex flex-col justify-around items-center">
    <p class="text-8xl text-neutral-200 font-outline-2 font-extrabold text-center">Witamy w sklepie Barbara</p>
    <UButton class="block text-2xl">Rozpocznij przeglądanie</UButton>
  </div>
</template>

<style scoped>
  #main-page {
    background-image: url("/main-page-bg.jpg");
    box-shadow: inset 0 0 600px 200px rgba(0, 0, 0, .8);
  }
</style>
