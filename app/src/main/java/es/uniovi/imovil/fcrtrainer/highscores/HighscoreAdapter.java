/*

Copyright 2014 Profesores y alumnos de la asignatura Informática Móvil de la EPI de Gijón

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */

package es.uniovi.imovil.fcrtrainer.highscores;

import java.util.ArrayList;

import es.uniovi.imovil.fcrtrainer.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HighscoreAdapter extends ArrayAdapter<Highscore> {

	private Context mContext;
	private ArrayList<Highscore> mHighscores;

	public HighscoreAdapter(Context context, ArrayList<Highscore> highscores) {
		super(context, R.layout.highscore_item, highscores);
		mContext = context;
		mHighscores = highscores;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.highscore_item, parent, false);

		TextView pos = (TextView) rowView.findViewById(R.id.text_view_position);
		pos.setText(Integer.toString(position + 1)); // Position 0 is 1st place

		TextView userName = (TextView) rowView.findViewById(R.id.text_view_user_name);
		userName.setText(mHighscores.get(position).getUserName());

		TextView score = (TextView) rowView.findViewById(R.id.text_view_score);
		score.setText(Integer.toString(mHighscores.get(position).getScore()));

		return rowView;
	}

}
