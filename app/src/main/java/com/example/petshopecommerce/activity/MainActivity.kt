package com.example.petshopecommerce.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.petshopecommerce.R
import com.example.petshopecommerce.adapter.BannerAdapter
import com.example.petshopecommerce.adapter.CategoryAdapter
import com.example.petshopecommerce.adapter.ItemAdapter
import com.example.petshopecommerce.databinding.ActivityMainBinding
import com.example.petshopecommerce.viewModel.MainViewModel

class MainActivity : BaseActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var recyclerViewBanner: ViewPager2
    private lateinit var recyclerViewCategoria: RecyclerView
    private lateinit var recyclerViewItem: RecyclerView

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        initBanner()
        initCategorias()
        initItem()
    }

    private fun initItem() {
        mainViewModel.listaItem.observe(this){lista ->
            if(lista.isNotEmpty()){
                binding.pbProdutosMain.visibility = View.GONE

                recyclerViewItem = binding.rvProdutosMain

                recyclerViewItem.adapter = ItemAdapter(lista)

                recyclerViewItem.layoutManager = GridLayoutManager(this, 2)

            } else{
                binding.pbProdutosMain.visibility = View.VISIBLE
            }
        }
    }

    private fun initCategorias() {
        mainViewModel.listaCategorias.observe(this){lista ->
            if(lista.isNotEmpty()){
                binding.pbReciclerViewCategoriasMain.visibility = View.GONE

                recyclerViewCategoria = binding.rvCategoriasMain

                recyclerViewCategoria.adapter = CategoryAdapter(lista)

                recyclerViewCategoria.layoutManager = LinearLayoutManager(
                    this,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )

            } else {
                binding.pbReciclerViewCategoriasMain.visibility = View.VISIBLE
            }
        }
    }

    private fun initBanner() {
        mainViewModel.listaBanner.observe(this) { lista ->
            if (lista.isNotEmpty()) {
                binding.pbViewPagerMain.visibility = View.GONE

                lista?.let {

                    recyclerViewBanner = findViewById<ViewPager2>(R.id.viewPagerSliderBanner)

                    recyclerViewBanner.adapter = BannerAdapter(lista)

                    //usado para determinar se o padding definidos no rv devem ser levados em consideracao
                    recyclerViewBanner.clipToPadding = false

                    //items do rv podem ser desenhados fora dos limites de seu proprio container
                    recyclerViewBanner.clipChildren = false

                    recyclerViewBanner.offscreenPageLimit = 3

                    //indica que a rolagem excessiva não será permitida para esse item
                    //sso significa que, quando o usuário tentar rolar o conteúdo além do início do RecyclerView, nenhum efeito de rolagem excessiva será exibido para o primeiro item filho.
                    recyclerViewBanner.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

                    val compositePageTransforme = CompositePageTransformer()
                    compositePageTransforme.addTransformer(MarginPageTransformer(40))

                    binding.viewPagerSliderBanner.setPageTransformer(compositePageTransforme)

                }

            } else {
                binding.pbViewPagerMain.visibility = View.VISIBLE
            }
        }
    }



    override fun onStart() {
        super.onStart()
        mainViewModel.recuperarBanners();
        mainViewModel.recuperarCategorias();
        mainViewModel.recuepraItem();
    }
}