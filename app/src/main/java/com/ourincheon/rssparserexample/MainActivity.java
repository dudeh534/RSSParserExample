package com.ourincheon.rssparserexample;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Vector;

/*RSS(Rich Site Summary OR Really Simple Syndication)는
* 뉴스나 블로그 사이트에서 주로 사용하는 컨텐츠 표현방식으로 웹 사이트 관리자는 RSS형식으로 웹 사이트 내용을 보여준다
* RSS는 XML 기반의 표준이다
* 이를 사이트 피드라고 하는데 이것은 새 기사들의 제목만, 또는 새 기사들 전체를 뽑아서 하나의 파일로 만들어 놓은 것을 말한다.
 * */
public class MainActivity extends ListActivity {
    GetData getData = new GetData();
    Vector<String> titlevec;
    Vector<String> descvec;
    /*
    java에서 동적인 길이로 여러 데이터형을 저장하기 위해서 Vector 클래스를 제공한다.
    Vector 클래스는 객체에 대한 참조값을 저장하는 배열이므로 다양한 객체들이 하나의 Vector에
    저장될 수 있고 길이도 필요에 따라 증감할 수 있다.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getData.execute();

        while(true){//안에 ENDTAG넣는거!
            try{
                Thread.sleep(1000);
                if(getData.flag == true){
                    titlevec = getData.titlevec;
                    descvec = getData.descvec;
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titlevec);
        ListView listView = getListView();
        setListAdapter(adapter);
        setContentView(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
